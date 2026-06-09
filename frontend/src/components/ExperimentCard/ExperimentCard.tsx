import { Experiment } from '../../model/Experiment'
import { useNavigate } from 'react-router-dom'
import styles from './experimentcard.module.css'

interface Props {
    experiment: Experiment
}

export default function ExperimentCard({ experiment }: Props) {
    const navigate = useNavigate()

    return (
        <div className={styles.card} onClick={() => navigate(`/experiment/${experiment.id}`)}>
            <p className={styles.experimentId}>EXP_{String(experiment.id).padStart(2, '0')}</p>
            <h2 className={styles.name}>{experiment.name}</h2>
            <p className={styles.description}>{experiment.description}</p>
        </div>
    )
}