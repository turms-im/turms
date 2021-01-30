export default class MessageAddition {
    public readonly isMentioned: boolean;
    public readonly mentionedUserIds: string[];
    public readonly recalledMessageIds: string[];

    constructor(isMentioned: boolean, mentionedUserIds: string[], recalledMessageIds: string[]) {
        this.isMentioned = isMentioned;
        this.mentionedUserIds = mentionedUserIds;
        this.recalledMessageIds = recalledMessageIds;
    }
}