import { useState } from "react"
import { useEffect } from "react"
import { getExperiments } from "../api/client";
import { Experiment } from "../model/Experiment";
import ExperimentCard from "../components/ExperimentCard/ExperimentCard";


export default function HomePage() {

    const [experiments, setExperiments] = useState<Experiment[]>([]);

    useEffect(() => {
        getExperiments()
            .then((res) => setExperiments(res.data))
            .catch((err) => console.error(err));

    }, [])

    const experimentList = experiments.map(
        experiment => <ExperimentCard
            key={experiment.id}
            experiment={experiment}
            />)

    return <section className="home">
        <header className="header">
            <h1>Praxis</h1>
            <p>Welcome to Praxis!</p>
        </header>

        {experimentList}


    </section>
}