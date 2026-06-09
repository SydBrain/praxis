export interface QuestionResult {
    questionId: number
    questionText: string
    userAnswer: string
    correctAnswer: string | null
    correct: boolean
    answerDistribution: Record<string, number>
    explanation: string | null
}