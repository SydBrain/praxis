import axios from 'axios'
import { Experiment } from '../model/Experiment'
import { Question } from '../model/Question'
import { SessionResult } from '../model/SessionResult'
import { UserSession } from '../model/UserSession'
import { UserAnswer } from '../model/UserAnswer'

const api = axios.create({
  baseURL: 'http://localhost:8080/api'
})

export const getExperiments = () => api.get<Experiment[]>('/experiments')
export const getQuestions = (experimentId: number) => api.get<Question[]>(`/experiments/${experimentId}/questions`)
export const createSession = (experimentId: number) => api.post<UserSession>('/sessions', { experimentId })
export const submitAnswers = (sessionId: number, userAnswers: UserAnswer[]) => api.post<UserAnswer[]>('/answers', { sessionId, userAnswers })
export const getResults = (sessionId: number) => api.get<SessionResult>(`/sessions/${sessionId}/results`)