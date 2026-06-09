import { useEffect, useState } from "react";
import { SessionResult } from "../model/SessionResult";
import { getResults } from "../api/client";

export function useResult(sessionId: number) {
    const [sessionResult, setSessionResult] = useState<SessionResult>();

    useEffect(() => {
        getResults(sessionId)
            .then((res) => setSessionResult(res.data))
            .catch((err) => console.error(err))
    }, []);

    return {
        sessionResult
    }
}