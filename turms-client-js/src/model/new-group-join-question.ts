export default class NewGroupJoinQuestion {
    public question: string;
    public answers: string[];
    public score: number;

    constructor(question: string, answers: string[], score: number) {
        this.question = question;
        this.answers = answers;
        this.score = score;
    }

}