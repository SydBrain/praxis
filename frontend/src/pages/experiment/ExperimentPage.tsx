import styles from './experiment.module.css'
import { useParams } from 'react-router-dom';
import { motion, AnimatePresence } from 'framer-motion'
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
            <div className={styles.header}>
                <span className={styles.experimentName}>Cognitive Reflection Test</span>
                <div className={styles.progressWrapper}>
                    <div className={styles.progressBar}>
                        <div
                            className={styles.progressFill}
                            style={{ width: `${((currentQuestionIndex + 1) / questions.length) * 100}%` }}
                        />
                    </div>
                    <span className={styles.progressText}>{currentQuestionIndex + 1} / {questions.length}</span>
                </div>
            </div>

            <AnimatePresence mode="wait">
                <motion.div
                    key={currentQuestionIndex}
                    className={styles.question}
                    initial={{ opacity: 0, x: 30 }}
                    animate={{ opacity: 1, x: 0 }}
                    exit={{ opacity: 0, x: -30 }}
                    transition={{ duration: 0.3, ease: 'easeInOut' }}
                >
                    <span className={styles.questionNumber}>
                        {String(currentQuestionIndex + 1).padStart(2, '0')}
                    </span>
                    <p className={styles.questionText}>{currentQuestion.text}</p>
                </motion.div>
            </AnimatePresence>

            <motion.div
                className={styles.inputArea}
                initial={{ opacity: 0, y: 20 }}
                animate={{ opacity: 1, y: 0 }}
                transition={{ duration: 0.4, delay: 0.2 }}
            >
                <input
                    className={styles.input}
                    type="text"
                    value={currentInput}
                    onChange={(e) => setCurrentInput(e.target.value)}
                    placeholder="Your answer..."
                    onKeyDown={(e) => e.key === 'Enter' && handleNext()}
                    autoFocus
                />
                <div className={styles.actions}>
                    {error
                        ? <p className={styles.error}>{error}</p>
                        : <span />
                    }
                    <button className={styles.button} onClick={handleNext}>
                        {currentQuestionIndex === questions.length - 1 ? 'Submit' : 'Next →'}
                    </button>
                </div>
            </motion.div>
        </div>
    )
}