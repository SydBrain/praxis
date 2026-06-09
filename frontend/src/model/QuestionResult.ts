export interface QuestionResult {
    questionId: number,
    questionText: string,
    userAnswer: string,
    correctAnswer: string,
    correct: boolean,
    answerDistribution: Record<string, number>
}