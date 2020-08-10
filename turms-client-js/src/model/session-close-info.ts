import TurmsCloseStatus from "./turms-close-status";

export interface SessionCloseInfo {
    closeStatus?: TurmsCloseStatus;
    webSocketStatusCode?: number;
    webSocketReason?: string;
    error?: Error;
}