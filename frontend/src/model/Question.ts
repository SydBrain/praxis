import { QuestionOption } from "./QuestionOption"

export interface Question {
    id: number
    text: string
    intuitiveAnswer: string
    correctAnswer: string
    questionType: 'FREE_TEXT' | 'SINGLE_CHOICE' | 'SLIDER'
    options: QuestionOption[]
    sliderMin: number | null
    sliderMax: number | null
    sliderStep: number | null
}