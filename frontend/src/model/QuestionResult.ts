export interface QuestionResult {
    questionId: number,
    questionText: string,
    userAnswer: string,
    correctAnswer: string,
    isCorrect: boolean,
    answerDistribution: Map<string, number>
}