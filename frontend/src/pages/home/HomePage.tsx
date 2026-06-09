import { useState } from "react"
import { useEffect } from "react"
import { getExperiments } from "../../api/client";
import { Experiment } from "../../model/Experiment";
import ExperimentCard from "../../components/ExperimentCard/ExperimentCard";
import styles from './homepage.module.css'
import { motion } from 'framer-motion'

export default function HomePage() {
    const [experiments, setExperiments] = useState<Experiment[]>([]);

    useEffect(() => {
        getExperiments()
            .then((res) => setExperiments(res.data))
            .catch((err) => console.error(err));
    }, [])

    const letters = "PRAXIS".split("")

    return (
        <div className={styles.page}>
            <motion.header
                className={styles.header}
                initial={{ opacity: 0, y: -20 }}
                animate={{ opacity: 1, y: 0 }}
                transition={{ duration: 0.5, ease: 'easeOut' }}
            >
                <div>
                    <div className={styles.accentBar} />
                    <p className={styles.label}>Behavioral Research Platform</p>
                    <h1 className={styles.title}>
                        {letters.map((letter, i) => (
                            <motion.span
                                key={i}
                                initial={{ opacity: 0, y: 20 }}
                                animate={{ opacity: 1, y: 0 }}
                                transition={{ duration: 0.4, delay: i * 0.08 }}
                                style={{ display: 'inline-block' }}
                            >
                                {letter}
                            </motion.span>
                        ))}
                        <span className={styles.cursor} />
                    </h1>
                </div>
                <p className={styles.subtitle}>
                    A series of cognitive experiments drawn from behavioral psychology literature.
                    Discover your biases and compare yourself to other participants.
                </p>
            </motion.header>

            <div className={styles.tickerWrapper}>
                <div className={styles.tickerTrack}>
                    {["COGNITIVE BIAS", "SYSTEM 1", "SYSTEM 2", "BEHAVIORAL RESEARCH", "DECISION MAKING", "HEURISTICS"].map((item, i) => (
                        <span key={i} className={styles.tickerItem}>— {item}</span>
                    ))}
                    {["COGNITIVE BIAS", "SYSTEM 1", "SYSTEM 2", "BEHAVIORAL RESEARCH", "DECISION MAKING", "HEURISTICS"].map((item, i) => (
                        <span key={`b${i}`} className={styles.tickerItem}>— {item}</span>
                    ))}
                </div>
            </div>

            <motion.p
                className={styles.experimentCount}
                initial={{ opacity: 0 }}
                animate={{ opacity: 1 }}
                transition={{ duration: 0.4, delay: 0.3 }}
            >
                Available experiments — {experiments.length}
            </motion.p>

            
            <motion.div
                className={styles.list}
                initial="hidden"
                animate="visible"
                variants={{
                    visible: { transition: { staggerChildren: 0.1, delayChildren: 0.4 } }
                }}
            >
                {experiments.map(experiment => (
                    <motion.div
                        key={experiment.id}
                        variants={{
                            hidden: { opacity: 0, y: 20 },
                            visible: { opacity: 1, y: 0 }
                        }}
                        transition={{ duration: 0.4, ease: 'easeOut' }}
                    >
                        <ExperimentCard experiment={experiment} />
                    </motion.div>
                ))}
            </motion.div>
        </div>
    )
}