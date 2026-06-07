export interface UserAnswer {
    id: number,
    sessionId: number,
    questionId: number,
    answerGiven: string,
    isCorrect: boolean
}