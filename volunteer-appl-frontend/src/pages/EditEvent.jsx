import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import api from "../axios/axios";
import "./EditEvent.css"

export default function EditEvent() {
    const { id } = useParams();
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        title: "",
        description: "",
        date: "",
        time: "",
        location: "",
        maxParticipants: "",
        category: ""
    });

    useEffect(() => {
        api.get(`/events/${id}`).then(res => {
            setFormData({
                ...res.data,
                time: res.data.time?.substring(0, 5) || ""
            });
        });
    }, [id]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const updateEvent = (id, data) => {
        return api.put(`/events/edit/${id}`, data, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem("token")}`
            }
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const updatedData = Object.fromEntries(
            Object.entries(formData).filter(
                ([_, value]) => value !== "" && value !== null
            )
        );

        // time назад во HH:mm:ss
        if (updatedData.time?.length === 5) {
            updatedData.time = `${updatedData.time}:00`;
        }

        try {
            await updateEvent(id, updatedData);
            navigate(`/events/${id}`);
        } catch (err) {
            console.error(err);
            alert("Error while updating event");
        }
    };

    return (
        <div className="edit-event-container">
            <form onSubmit={handleSubmit}>
                <h2>Edit Event</h2>
                <input name="title" value={formData.title} onChange={handleChange} />
                <textarea name="description" value={formData.description} onChange={handleChange} />
                <input type="date" name="date" value={formData.date} onChange={handleChange} />
                <input type="time" name="time" value={formData.time} onChange={handleChange} />
                <input name="location" value={formData.location} onChange={handleChange} />
                <input type="number" name="maxParticipants" value={formData.maxParticipants} onChange={handleChange} />
                <input name="category" value={formData.category} onChange={handleChange} />
                <button type="submit">Update</button>
            </form>
        </div>
    );
}
