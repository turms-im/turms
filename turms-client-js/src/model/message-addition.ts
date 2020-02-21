export default class MessageAddition {
    public isMentioned: boolean;
    public mentionedUserIds: string[];

    constructor(isMentioned: boolean, mentionedUserIds: string[]) {
        this.isMentioned = isMentioned;
        this.mentionedUserIds = mentionedUserIds;
    }
}