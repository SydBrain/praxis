import { useParams, useNavigate } from 'react-router-dom';
import styles from './results.module.css'
import { useResult } from '../../hooks/useResult';
import AnswerDistributionChart from '../../components//DistributionChart/AnswerDistributionChart';
import { motion } from 'framer-motion'

export default function ResultsPage() {
    const { sessionId } = useParams();
    const { sessionResult } = useResult(parseInt(sessionId as string));
    const navigate = useNavigate();

    if (!sessionResult) return <div className={styles.loading}>LOADING...</div>

    return (
        <div className={styles.page}>
            <motion.header
                className={styles.header}
                initial={{ opacity: 0, y: -20 }}
                animate={{ opacity: 1, y: 0 }}
                transition={{ duration: 0.5 }}
            >
                <div>
                    <p className={styles.label}>Session Complete</p>
                    <h1 className={`${styles.score} ${sessionResult.score === sessionResult.total ? styles.scoreHigh : sessionResult.score === 0 ? styles.scoreLow : ''}`}>
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
            </motion.header>

            <div className={styles.tickerWrapper}>
                <div className={styles.tickerTrack}>
                    {["COGNITIVE BIAS", "SYSTEM 1 RESPONSE", "HEURISTICS", "DECISION MAKING", "BEHAVIORAL DATA", "REFLECTION INDEX"].map((item, i) => (
                        <span key={i} className={styles.tickerItem}>— {item}</span>
                    ))}
                    {["COGNITIVE BIAS", "SYSTEM 1 RESPONSE", "HEURISTICS", "DECISION MAKING", "BEHAVIORAL DATA", "REFLECTION INDEX"].map((item, i) => (
                        <span key={`b${i}`} className={styles.tickerItem}>— {item}</span>
                    ))}
                </div>
            </div>

            <motion.div
                className={styles.questions}
                initial="hidden"
                animate="visible"
                variants={{
                    visible: { transition: { staggerChildren: 0.15, delayChildren: 0.3 } }
                }}
            >
                {sessionResult.questions.map((q, i) => (
                    <motion.div
                        key={q.questionId}
                        variants={{
                            hidden: { opacity: 0, y: 20 },
                            visible: { opacity: 1, y: 0 }
                        }}
                        transition={{ duration: 0.4, ease: 'easeOut' }}
                        className={`${styles.questionBlock} ${q.correct ? styles.correct : styles.incorrect}`}
                    >
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
                    </motion.div>
                ))}
            </motion.div>

            <motion.footer
                className={styles.footer}
                initial={{ opacity: 0 }}
                animate={{ opacity: 1 }}
                transition={{ duration: 0.4, delay: 0.6 }}
            >
                <button className={styles.button} onClick={() => navigate('/')}>
                    ← Back to experiments
                </button>
            </motion.footer>
        </div>
    )
}