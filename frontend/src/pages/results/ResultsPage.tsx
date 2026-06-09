import { useParams, useNavigate } from 'react-router-dom';
import styles from './results.module.css'
import { useResult } from '../../hooks/useResult';
import AnswerDistributionChart from '../../components//DistributionChart/AnswerDistributionChart';

export default function ResultsPage() {
    const { sessionId } = useParams();
    const { sessionResult } = useResult(parseInt(sessionId as string));
    const navigate = useNavigate();

    if (!sessionResult) return <div className={styles.loading}>LOADING...</div>

    return (
        <div className={styles.page}>
            <header className={styles.header}>
                <div>
                    <p className={styles.label}>Session Complete</p>
                    <h1 className={styles.score}>
                        {sessionResult.score}<span className={styles.scoreTotal}>/{sessionResult.total}</span>
                    </h1>
                </div>
                <p className={styles.scoreLabel}>
                    {sessionResult.score === sessionResult.total
                        ? 'Perfect score — System 2 thinking throughout.'
                        : sessionResult.score === 0
                        ? 'All intuitive answers — typical System 1 response.'
                        : `${sessionResult.total - sessionResult.score} intuitive answer${sessionResult.total - sessionResult.score > 1 ? 's' : ''} detected.`
                    }
                </p>
            </header>

            <div className={styles.questions}>
                {sessionResult.questions.map((q, i) => (
                    <div key={q.questionId} className={`${styles.questionBlock} ${q.correct ? styles.correct : styles.incorrect}`}>
                        <div className={styles.questionHeader}>
                            <span className={styles.questionNumber}>Q{i + 1}</span>
                            <span className={styles.verdict}>{q.correct ? 'CORRECT' : 'INCORRECT'}</span>
                        </div>
                        <p className={styles.questionText}>{q.questionText}</p>
                        <div className={styles.answers}>
                            <div className={styles.answerRow}>
                                <span className={styles.answerLabel}>YOUR ANSWER</span>
                                <span className={styles.answerValue}>{q.userAnswer}</span>
                            </div>
                            <div className={styles.answerRow}>
                                <span className={styles.answerLabel}>CORRECT</span>
                                <span className={styles.answerValue}>{q.correctAnswer}</span>
                            </div>
                        </div>
                        <div className={styles.chart}>
                            <p className={styles.chartLabel}>ANSWER DISTRIBUTION — ALL PARTICIPANTS</p>
                            <AnswerDistributionChart
                                distribution={q.answerDistribution}
                                correctAnswer={q.correctAnswer}
                                userAnswer={q.userAnswer}
                            />
                        </div>
                    </div>
                ))}
            </div>

            <footer className={styles.footer}>
                <button className={styles.button} onClick={() => navigate('/')}>
                    ← Back to experiments
                </button>
            </footer>
        </div>
    )
}