import UserLocation from "../model/user-location";
import { im } from "../model/proto-bundle";
import UserStatus = im.turms.proto.UserStatus;
import DeviceType = im.turms.proto.DeviceType;
export interface UserInfo {
    userId?: string;
    userOnlineStatus?: UserStatus;
    deviceType?: DeviceType;
    location?: UserLocation;
}
export default class StateStore {
    private _websocket?;
    private _isConnected;
    private _connectionRequestId?;
    private _sessionId?;
    private _lastRequestDate;
    private _userInfo;
    get websocket(): WebSocket;
    set websocket(value: WebSocket);
    get isConnected(): boolean;
    set isConnected(value: boolean);
    get connectionRequestId(): number;
    set connectionRequestId(value: number);
    get sessionId(): string;
    set sessionId(value: string);
    get lastRequestDate(): Date;
    set lastRequestDate(value: Date);
    get userInfo(): UserInfo;
    set userInfo(value: UserInfo);
}
