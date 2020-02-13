export default class MessageAddition {
    public isMentioned: boolean;
    public mentionedUserIds: number[];

    constructor(isMentioned: boolean, mentionedUserIds: number[]) {
        this.isMentioned = isMentioned;
        this.mentionedUserIds = mentionedUserIds;
    }
}