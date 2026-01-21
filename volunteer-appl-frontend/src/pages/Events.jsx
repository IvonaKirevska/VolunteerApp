import {use, useEffect, useState} from "react";
import api from "../axios/axios.js";
import EventCard from "../components/EventCard.jsx";
import "./Events.css";
import Snowfall from "react-snowfall";

export default function Events() {
    const [events, setEvents] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");

    useEffect(() => {
        api.get("/events")
            .then(res => {
                setEvents(res.data);
                setLoading(false);
            })
            .catch(err => {
                setError("Failed to load events");
                setLoading(false);
            });
    }, []);

    if (loading) return <p>Loading events...</p>
    if (error) return <p>{error}</p>

    return (
        <div>
            <Snowfall color="#82C3D9"/>
            <div className="events-container">
                <h1>Events</h1>

                <div className="events-grid">
                    {events.map(event => (
                        <EventCard key={event.id} event={event}/>
                    ))}
                </div>
            </div>
        </div>
    );
}