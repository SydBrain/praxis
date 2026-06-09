import styles from './SliderInput.module.css'

interface Props {
    value: string
    onChange: (value: string) => void
    min: number
    max: number
    step: number
}

export default function SliderInput({ value, onChange, min, max, step }: Props) {
    return (
        <div className={styles.wrapper}>
            <div className={styles.track}>
                <input
                    className={styles.slider}
                    type="range"
                    min={min}
                    max={max}
                    step={step}
                    value={value}
                    onChange={(e) => onChange(e.target.value)}
                />
            </div>
            <div className={styles.labels}>
                <span className={styles.label}>{min}</span>
                <span className={styles.value}>{value}</span>
                <span className={styles.label}>{max}</span>
            </div>
        </div>
    )
}