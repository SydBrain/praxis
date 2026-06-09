import { useEffect, useState } from "react";
import { Question } from "../model/Question";
import { UserSession } from "../model/UserSession";
import { useNavigate, useParams } from "react-router-dom";
import { createSession, getQuestions, submitAnswers } from "../api/client";

export function useExperiment(experimentId: number) {
    const [userSession, setUserSession] = useState<UserSession>();
    const [questions, setQuestions] = useState<Question[]>([]);
    const [currentQuestionIndex, setCurrentQuestionIndex] = useState<number>(0);
    const [userAnswers, setUserAnswers] = useState<string[]>([]);
    const [currentInput, setCurrentInput] = useState<string>('');
    const [error, setError] = useState<string>('')

    const navigate = useNavigate();

    const currentQuestion = questions[currentQuestionIndex];

    useEffect(() => {
        createSession(experimentId)
            .then((res) => setUserSession(res.data))
            .catch((err) => console.error(err));
        getQuestions(experimentId)
            .then((res) => setQuestions(res.data))
            .catch((err) => console.error(err));
    }, [])

    const handleNext = () => {
        const updatedAnswers = [...userAnswers, currentInput];
        setUserAnswers(updatedAnswers);
        setCurrentInput('');

        const isLast = currentQuestionIndex === questions.length - 1;

        if (isLast) {
            const payload = questions.map((q, i) => ({
                questionId: q.id,
                answerGiven: updatedAnswers[i]
            }));
            submitAnswers(userSession!.id as number, payload)
                .then(() => navigate(`/results/${userSession!.id}`))
                .catch((err) => console.error(err));
        } else {
            setCurrentQuestionIndex(currentQuestionIndex + 1);
        }

        const isValidNumber = !isNaN(parseFloat(currentInput)) && currentInput.trim() !== ''

        if (!isValidNumber) {
            setError('Please enter a numeric value')
            return
        }
        setError('')
    }

    return {
        currentQuestion,
        currentQuestionIndex,
        questions,
        currentInput,
        setCurrentInput,
        handleNext,
        error
    }
}