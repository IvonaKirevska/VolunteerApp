import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import api from "../axios/axios";
import "./EventDetails.css";

export default function EventDetails() {
    const { id } = useParams();
    const [event, setEvent] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");
    const [participants, setParticipants] = useState([]);

    useEffect(() => {
        api.get(`/events/${id}`)
            .then(res => {
                setEvent(res.data);
                setLoading(false);
            })
            .catch(err => {
                setError("Failed to load event details");
                setLoading(false);
            });
    }, [id]);

    useEffect(() => {
        api.get(`/participations/${id}/participants`, {
            headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
        })
            .then(res => setParticipants(res.data))
            .catch(err => console.log(err));
    }, [id]);

    if (loading) return <p>Loading...</p>;
    if (error) return <p>{error}</p>;

    return (
        <div className="event-details">
            <h1>{event.title}</h1>
            <p>{event.description}</p>
            <p>Date: {event.date}</p>
            <p>Time: {event.time}</p>
            <p>Location: {event.location}</p>
            <p>Organizer: {event.organizerName}</p>
            <p>Max Participants: {event.maxParticipants}</p>
            <p>Category: {event.category}</p>

            <h2>Participants:</h2>
            {participants.length === 0 ? (
                <p>No participants yet</p>
            ) : (
                <ul>
                    {participants.map((name, index) => (
                        <li key={index}>{name}</li>
                    ))}
                </ul>
            )}
        </div>

    );

}
