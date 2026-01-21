import "./EventCard.css";
import api from "../axios/axios";
import {useNavigate} from "react-router-dom"

export default function EventCard({event}){

    const navigate = useNavigate();

    const handleApply = async (eventId) => {
        console.log("Token:", localStorage.getItem("token"));
        try {
            await api.post(`/participations/${eventId}/join`, {}, {
                headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
            });
            alert("Successfully applied!");
        } catch (err) {
            alert(err.response?.data || "Failed to apply");
        }
    };

    const goToDetails = () => {
        navigate(`/events/${event.id}`);
    }

    return(
        <div className="event-card">
            <h3>{event.title}</h3>
            <p className="description">{event.description}</p>

            <div className="details">
                <span>📅 {event.date}</span>
                <span>⏰ {event.time}</span>
                <span>📍 {event.location}</span>
                <span>👥 Max: {event.maxParticipants}</span>
                <span>👤 Организира: {event.organizerName}</span>
            </div>

            <button className="apply-btn" onClick={()=>handleApply(event.id)}>Apply</button>
            <button className="details-btn" onClick={goToDetails}>Details</button>
        </div>
    );
}