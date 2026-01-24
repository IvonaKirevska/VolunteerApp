import "./ConfirmModal.css";
import {useEffect} from "react";

export default function ConfirmModal({
                                         show,
                                         title,
                                         message,
                                         onConfirm,
                                         onCancel,
                                         onClose,
                                         confirmText = "Ok",
                                         cancelText = "Cancel",
                                         danger = false,
                                         type = "confirm",
                                         autoCloseDuration = 2000
                                     }) {
    if (!show) return null;

    return (
        <div className="modal-backdrop">
            <div className={`modal-box ${type}`}>
                <h2>{title}</h2>
                <p>{message}</p>

                {type === "success" ? (
                    <div className="modal-actions">
                        <button className="confirm-btn" onClick={onCancel}>
                            {confirmText}
                        </button>
                    </div>
                ) : (
                    <div className="modal-actions">
                        <button className="cancel-btn" onClick={onCancel}>{cancelText}</button>
                        <button
                            className={danger ? "confirm-btn danger" : "confirm-btn"}
                            onClick={onConfirm}
                        >
                            {confirmText}
                        </button>
                    </div>
                )}
            </div>
        </div>
    );
}
