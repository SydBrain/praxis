import { BarChart, Bar, XAxis, YAxis, Tooltip, ResponsiveContainer, Cell } from 'recharts'

interface Props {
    distribution: Record<string, number>
    correctAnswer?: string
    userAnswer: string
}

export default function AnswerDistributionChart({ distribution, correctAnswer, userAnswer }: Props) {
    const data = Object.entries(distribution).map(([answer, count]) => ({
        answer,
        count
    }))

    const getColor = (answer: string) => {
        if (answer === correctAnswer) return '#000000'
        if (answer === userAnswer) return '#cccccc'
        return '#e0e0e0'
    }

    return (
        <ResponsiveContainer width="100%" height={200}>
            <BarChart data={data} margin={{ top: 0, right: 0, left: -20, bottom: 0 }}>
                <XAxis
                    dataKey="answer"
                    tick={{ fontFamily: 'IBM Plex Mono', fontSize: 11 }}
                    axisLine={{ stroke: '#000000' }}
                    tickLine={false}
                />
                <YAxis
                    allowDecimals={false}
                    tick={{ fontFamily: 'IBM Plex Mono', fontSize: 11 }}
                    axisLine={false}
                    tickLine={false}
                />
                <Tooltip
                    contentStyle={{
                        fontFamily: 'IBM Plex Mono',
                        fontSize: 11,
                        border: '1px solid #000',
                        borderRadius: 0
                    }}
                />
                <Bar dataKey="count" radius={0}>
                    {data.map((entry) => (
                        <Cell key={entry.answer} fill={getColor(entry.answer)} />
                    ))}
                </Bar>
            </BarChart>
        </ResponsiveContainer>
    )
}