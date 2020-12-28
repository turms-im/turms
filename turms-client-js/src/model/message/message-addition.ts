export default class MessageAddition {
    public isMentioned: boolean;
    public mentionedUserIds: string[];
    public recalledMessageIds: string[];

    constructor(isMentioned: boolean, mentionedUserIds: string[], recalledMessageIds: string[]) {
        this.isMentioned = isMentioned;
        this.mentionedUserIds = mentionedUserIds;
        this.recalledMessageIds = recalledMessageIds;
    }
}