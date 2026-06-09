import styles from './SingleChoiceInput.module.css'
import { QuestionOption } from '../../model/QuestionOption'

interface Props {
    options: QuestionOption[]
    value: string
    onChange: (value: string) => void
}

export default function SingleChoiceInput({ options, value, onChange }: Props) {
    return (
        <div className={styles.options}>
            {options.map((opt) => (
                <button
                    key={opt.id}
                    className={`${styles.option} ${value === opt.text ? styles.selected : ''}`}
                    onClick={() => onChange(opt.text)}
                >
                    {opt.text}
                </button>
            ))}
        </div>
    )
}