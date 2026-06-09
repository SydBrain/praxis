import { useState } from "react"
import { useEffect } from "react"
import { getExperiments } from "../../api/client";
import { Experiment } from "../../model/Experiment";
import ExperimentCard from "../../components/ExperimentCard/ExperimentCard";
import styles from './homepage.module.css'

export default function HomePage() {

    const [experiments, setExperiments] = useState<Experiment[]>([]);

    useEffect(() => {
        getExperiments()
            .then((res) => setExperiments(res.data))
            .catch((err) => console.error(err));

    }, [])

    const experimentList = experiments.map(
        experiment => <ExperimentCard
            key={experiment.id}
            experiment={experiment}
            />)

    return (
    <div className={styles.page}>
        <header className={styles.header}>
            <div>
                <p className={styles.label}>Behavioral Research Platform</p>
                <h1 className={styles.title}>PRAXIS</h1>
            </div>
            <p className={styles.subtitle}>
                A series of cognitive experiments drawn from behavioral psychology literature.
                Discover your biases and compare yourself to other participants.
            </p>
        </header>
        <p className={styles.experimentCount}>
            Available experiments — {experiments.length}
        </p>
        <div className={styles.list}>
            {experimentList}
        </div>
    </div>
)
}

