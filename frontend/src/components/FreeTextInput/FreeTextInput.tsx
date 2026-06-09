import styles from './FreeTextInput.module.css'

interface Props {
    value: string
    onChange: (value: string) => void
    onSubmit: () => void
}

export default function FreeTextInput({ value, onChange, onSubmit }: Props) {
    return (
        <input
            className={styles.input}
            type="text"
            value={value}
            onChange={(e) => onChange(e.target.value)}
            placeholder="Your answer..."
            onKeyDown={(e) => e.key === 'Enter' && onSubmit()}
            autoFocus
        />
    )
}