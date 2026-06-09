import { useEffect, useState } from "react";
import { Question } from "../model/Question";
import { UserSession } from "../model/UserSession";
import { useNavigate } from "react-router-dom";
import { createSession, getExperiment, getQuestions, submitAnswers } from "../api/client";

export function useExperiment(experimentId: number) {
    const [userSession, setUserSession] = useState<UserSession>();
    const [questions, setQuestions] = useState<Question[]>([]);
    const [currentQuestionIndex, setCurrentQuestionIndex] = useState<number>(0);
    const [userAnswers, setUserAnswers] = useState<string[]>([]);
    const [currentInput, setCurrentInput] = useState<string>('');
    const [error, setError] = useState<string>('')
    const navigate = useNavigate();
    const currentQuestion = questions[currentQuestionIndex];
    const [experimentName, setExperimentName] = useState<string>('')

    useEffect(() => {
        createSession(experimentId)
            .then((res) => setUserSession(res.data))
            .catch((err) => console.error(err));
        getQuestions(experimentId)
            .then((res) => setQuestions(res.data))
            .catch((err) => console.error(err));
        getExperiment(experimentId)
            .then((res) => setExperimentName(res.data.name))
            .catch((err) => console.error(err));
    }, []);

    useEffect(() => {
        if (!currentQuestion) return
        if (currentQuestion.questionType === 'SLIDER') {
            const mid = Math.round((currentQuestion.sliderMin! + currentQuestion.sliderMax!) / 2)
            setCurrentInput(String(mid))
        } else {
            setCurrentInput('')
        }
    }, [currentQuestionIndex, questions])

    const handleNext = () => {
        if (currentQuestion.questionType === 'FREE_TEXT') {
            const isValidNumber = !isNaN(parseFloat(currentInput)) && currentInput.trim() !== ''
            if (!isValidNumber) {
                setError('Please enter a numeric value')
                return
            }
        }

        setError('')
        const updatedAnswers = [...userAnswers, currentInput];
        setUserAnswers(updatedAnswers);
        setCurrentInput('');

        const isLast = currentQuestionIndex === questions.length - 1;
        if (isLast) {
            const payload = questions.map((q, i) => ({
                questionId: q.id,
                answerGiven: updatedAnswers[i]
            }));
            submitAnswers(userSession!.id, payload)
                .then(() => navigate(`/results/${userSession!.id}`))
                .catch((err) => console.error(err));
        } else {
            setCurrentQuestionIndex(currentQuestionIndex + 1);
        }
    }

    return {
        currentQuestion,
        currentQuestionIndex,
        questions,
        currentInput,
        setCurrentInput,
        handleNext,
        error,
        experimentName
    }
}