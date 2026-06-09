import { useEffect, useState } from 'react';
import { UserSession } from '../../model/UserSession';
import styles from './experiment.module.css'
import { Question } from '../../model/Question';
import { useParams, useNavigate } from 'react-router-dom';
import { createSession, getQuestions, submitAnswers } from '../../api/client';
import { useExperiment } from '../../hooks/useExperiment';

export default function ExperimentPage() {

    const { id } = useParams();
    const experimentId = parseInt(id as string);

    const { 
        currentQuestion, 
        currentInput, 
        setCurrentInput, 
        handleNext, 
        questions, 
        currentQuestionIndex,
        error
    } = useExperiment(experimentId)

    if (!currentQuestion) return <div>Loading...</div>

    return (
        <div className={styles.page}>
            <div className={styles.progress}>
                {currentQuestionIndex + 1} / {questions.length}
            </div>
            <div className={styles.question}>
                <p className={styles.questionText}>{currentQuestion.text}</p>
            </div>
            <div className={styles.inputArea}>
                <input
                    className={styles.input}
                    type="text"
                    value={currentInput}
                    onChange={(e) => setCurrentInput(e.target.value)}
                    placeholder="Your answer..."
                    onKeyDown={(e) => e.key === 'Enter' && handleNext()}
                />
                <button className={styles.button} onClick={handleNext}>
                    {currentQuestionIndex === questions.length - 1 ? 'Submit' : 'Next →'}
                </button>
            </div>
            {error && <p className={styles.error}>{error}</p>}
        </div>
    )
}