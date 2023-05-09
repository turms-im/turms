<template>
    <content-template
        :name="name"
        :url="url"
        :filters="filters"
        :actions="actions"
        :table="table"
    />
</template>

<script>
import ContentTemplate from '../../template/content-template.vue';

export default {
    name: 'content-group-question-pane',
    components: {
        ContentTemplate
    },
    data() {
        return {
            name: 'group-question',
            url: this.$rs.apis.groupQuestion,
            filters: [
                {
                    type: 'INPUT',
                    name: 'ids',
                    placeholder: 'groupQuestionId',
                    rules: {
                        noBlank: true
                    }
                },
                {
                    type: 'INPUT',
                    name: 'groupIds',
                    placeholder: 'groupId'
                }
            ],
            actions: [
                {
                    title: 'addGroupQuestion',
                    type: 'CREATE',
                    fields: [
                        {
                            id: 'groupId',
                            type: 'INPUT',
                            rules: this.$validator.create({required: true, onlyNumber: true})
                        },
                        {
                            id: 'question',
                            type: 'INPUT',
                            rules: this.$validator.create({required: true})
                        },
                        {
                            id: 'score',
                            type: 'INPUT',
                            rules: this.$validator.create({required: true, onlyNumber: true})
                        },
                        {
                            id: 'answers',
                            type: 'DYNAMIC-INPUT',
                            label: 'answer',
                            placeholder: 'correctAnswer',
                            addButtonLabel: 'addCorrectAnswer',
                            rules: [{
                                required: true,
                                messageId: 'operateAnswerPrompt'
                            }]
                        }
                    ]
                },
                {
                    title: 'updateSelectedGroupQuestions',
                    type: 'UPDATE',
                    fields: [
                        {
                            id: 'question',
                            type: 'INPUT'
                        },
                        {
                            id: 'score',
                            type: 'INPUT',
                            rules: this.$validator.create({onlyNumber: true})
                        },
                        {
                            id: 'answers',
                            type: 'DYNAMIC-INPUT',
                            label: 'answer',
                            placeholder: 'correctAnswer',
                            addButtonLabel: 'addCorrectAnswer',
                            rules: {
                                required: true,
                                messageId: 'operateAnswerPrompt'
                            }
                        }
                    ]
                }
            ],
            table: {
                columns: [{
                    title: 'questionId',
                    key: 'id',
                    width: '15%'
                },
                {
                    key: 'groupId',
                    width: '15%'
                },
                {
                    key: 'question',
                    width: '25%'
                },
                {
                    title: 'answer',
                    key: 'answers',
                    width: '25%'
                },
                {
                    key: 'score',
                    width: '10%'
                },
                {
                    key: 'operation',
                    width: '10%'
                }]
            }
        };
    }
};
</script>