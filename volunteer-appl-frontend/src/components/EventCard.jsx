import "./EventCard.css";
import api from "../axios/axios";
import {useNavigate} from "react-router-dom"
import {useState} from "react";
import ConfirmModal from "./ConfirmModal.jsx";

export default function EventCard({event}){

    const navigate = useNavigate();
    const [showSuccess, setShowSuccess] = useState(false);

    const handleApply = async (eventId) => {
        try {
            await api.post(`/participations/${eventId}/join`, {}, {
                headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
            });
            setShowSuccess(true);
        } catch (err) {
           console.log(err);
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

            <ConfirmModal
                show={showSuccess}
                title="Success"
                message="Successfully applied to the event!"
                type="success"
                onCancel={()=>setShowSuccess(false)}
            />
        </div>
    );
}