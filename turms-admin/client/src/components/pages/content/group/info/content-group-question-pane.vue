<template>
    <content-template
        :name="name"
        :url="url"
        :filters="filters"
        :action-groups="actionGroups"
        :table="table"
    />
</template>

<script>
import ContentTemplate from '../../template/content-template';

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
                    model: '',
                    name: 'ids',
                    placeholder: this.$t('groupQuestionId'),
                    rules: {
                        noBlank: true
                    }
                },
                {
                    type: 'INPUT',
                    model: '',
                    name: 'groupIds',
                    placeholder: this.$t('groupId')
                }
            ],
            actionGroups: [
                [{
                    title: this.$t('addGroupQuestion'),
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
                            label: this.$t('answer'),
                            placeholder: this.$t('correctAnswer'),
                            addButtonLabel: this.$t('addCorrectAnswer'),
                            rules: this.$validator.create({required: true},
                                {
                                    validateTrigger: ['change', 'blur'],
                                    rules: [
                                        {
                                            required: true,
                                            whitespace: true,
                                            message: this.$t('operateAnswerPrompt')
                                        }
                                    ]
                                })
                        }
                    ]
                },
                {
                    title: this.$t('updateSelectedGroupQuestions'),
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
                            label: this.$t('answer'),
                            placeholder: this.$t('correctAnswer'),
                            addButtonLabel: this.$t('addCorrectAnswer'),
                            rules: this.$validator.create(
                                {
                                    validateTrigger: ['change', 'blur'],
                                    rules: [
                                        {
                                            required: true,
                                            whitespace: true,
                                            message: this.$t('operateAnswerPrompt')
                                        }
                                    ]
                                })
                        }
                    ]
                }]
            ],
            table: {
                columns: [{
                    title: this.$t('questionId'),
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
                    title: this.$t('answer'),
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