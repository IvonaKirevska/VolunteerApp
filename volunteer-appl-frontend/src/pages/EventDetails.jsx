import {Link, redirect, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import api from "../axios/axios";
import "./EventDetails.css";
import {useNavigate} from "react-router-dom";
import ConfirmModal from "../components/ConfirmModal.jsx";

export default function EventDetails() {
    const {id} = useParams();
    const [event, setEvent] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");
    const [participants, setParticipants] = useState([]);
    const loggedUser=localStorage.getItem("username");
    const navigate = useNavigate();
    const [showLeaveModal, setShowLeaveModal] = useState(false);
    const [showDeleteModal, setShowDeleteModal] = useState(false);

    const handleLeave = async (eventId) => {
        try {
            await api.delete(
                `/participations/${eventId}/leave`,
                {
                    headers: {
                        Authorization: `Bearer ${localStorage.getItem("token")}`
                    }
                }
            );
            window.location.reload();
        } catch (err) {
            alert(err.response?.data || "Failed to leave");
        }
    };

    const handleDelete=async (eventId) =>{
        try{
            await api.delete(`events/delete/${eventId}`,{
                headers: {Authorization: `Bearer ${localStorage.getItem("token")}`}
            })
            navigate("/events");
        }catch (err){
            alert(err.response?.data || "Failed to delete event");
        }
    }


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
            headers: {Authorization: `Bearer ${localStorage.getItem("token")}`}
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


            {!(event.organizerName === loggedUser)&&(
            <button className="leave-btn" onClick={() => setShowLeaveModal(true)}>Leave Event</button>
            )}
            {event.organizerName ===loggedUser && (
                <Link to={`/events/${event.id}/edit`}>
                    <button className="edit-btn">Edit Event</button>
                </Link>
            )}

            {event.organizerName===loggedUser&&(
                <button className="delete-btn" onClick={() => setShowDeleteModal(true)}>Delete Event</button>
            )}

            <ConfirmModal
                show={showLeaveModal}
                title="Leave Event"
                message="Are you sure you want to leave this event?"
                confirmText="Leave"
                onCancel={()=>setShowLeaveModal(false)}
                onConfirm={() => handleLeave(event.id)}
            />

            <ConfirmModal
                show={showDeleteModal}
                title="Delete Event"
                message="Are you sure you want to delete this event?"
                confirmText="Delete"
                onCancel={()=>setShowDeleteModal(false)}
                onConfirm={() => handleDelete(event.id)}
            />
        </div>

    );

}
