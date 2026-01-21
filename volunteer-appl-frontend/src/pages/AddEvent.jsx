import {useState} from "react";
import {useNavigate} from "react-router-dom";
import api from "../axios/axios";
import "./AddEvent.css";

export default function AddEvent() {
    const navigate = useNavigate();
    const [form, setForm] = useState({
        title: "",
        description: "",
        date: "",
        time: "",
        location: "",
        maxParticipants: "",
        category: "",
    });
    const [error, setError] = useState("");

    const handleChange = (e) => {
        setForm({
            ...form,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            await api.post("/events/add", {
                ...form,
                time: form.time.length === 5 ? `${form.time}:00` : form.time,
                maxParticipants: Number(form.maxParticipants),
            }, {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("token")}`
                }
            });
            navigate("/events");
        } catch (err) {
            console.log(err.response?.data);
            setError("Не успеа да се додаде настанот");
        }
    };

    return (
        <div className="add-event-container">
            <h2>Add Event</h2>
            {error && <p className="error">{error}</p>}
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    name="title"
                    placeholder="Title"
                    value={form.title}
                    onChange={handleChange}
                    required
                />
                <textarea
                    name="description"
                    placeholder="Description"
                    value={form.description}
                    onChange={handleChange}
                    required
                />
                <input
                    type="date"
                    name="date"
                    value={form.date}
                    onChange={handleChange}
                    required
                />
                <input
                    type="time"
                    name="time"
                    value={form.time}
                    onChange={handleChange}
                    required
                />
                <input
                    type="text"
                    name="location"
                    placeholder="Location"
                    value={form.location}
                    onChange={handleChange}
                    required
                />
                <input
                    type="number"
                    name="maxParticipants"
                    placeholder="Max Participants"
                    value={form.maxParticipants}
                    onChange={handleChange}
                    required
                />
                <input
                    type="text"
                    name="category"
                    placeholder="Category"
                    value={form.category}
                    onChange={handleChange}
                    required
                />
                <button type="submit">Add Event</button>
            </form>
        </div>
    );
}