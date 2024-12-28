#ifndef TURMS_CLIENT_MODEL_NEW_GROUP_JOIN_QUESTION_H
#define TURMS_CLIENT_MODEL_NEW_GROUP_JOIN_QUESTION_H

#include <string>
#include <unordered_set>

namespace turms::client::model {

struct NewGroupJoinQuestion {
    std::string question;
    std::unordered_set<std::string> answers;
    int score;
};

}  // namespace turms::client::model

#endif  // TURMS_CLIENT_MODEL_NEW_GROUP_JOIN_QUESTION_H