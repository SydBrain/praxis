import { QuestionResult } from "./QuestionResult";

export interface SessionResult {
    score: number
    total: number
    questions: QuestionResult[]
    hasScore: boolean
}