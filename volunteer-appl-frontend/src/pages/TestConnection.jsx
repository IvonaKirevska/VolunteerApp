import { useEffect, useState } from "react";
import api from "../axios/axios.js";

export default function TestConnection() {
    const [data, setData] = useState(null);

    useEffect(() => {
        api.get("/events")
            .then(res => setData(res.data))
            .catch(err => setData("Error: " + err.message));
    }, []);

    return (
        <div>
            <h1>Backend Test</h1>
            <pre>{JSON.stringify(data, null, 2)}</pre>
        </div>
    );
}
