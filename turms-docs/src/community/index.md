# Community

## FAQ

### Why does Issues use English?

The fundamental reason: Issues are written in a single language to facilitate searching. During the use of Issues, encountering open source projects that use multiple languages is the most troublesome because when searching for a problem, such as "How is the blocklist mechanism implemented in the Turms server", for bilingual projects, we usually need to search for both "黑名单" and "blocklist" keywords. In other words, at least two searches are needed to ensure that all related Issues are found, resulting in a poor user search experience. However, if Issues are only in English, users only need to search for the "blocklist" keyword.

Secondary reason: using English facilitates global open source and promotion, while using non-English languages goes against our open source philosophy.

In addition, we do not exclude users from submitting Issues in non-English languages, but encourage them to use English more often. However, we will always reply in English.

### Why are There no QQ Groups, WeChat Groups, Slack Channels, or Other Groups?

Using various groups for issues management and discussion is a very bad practice, and issues management should have been prioritized using GitHub's Issues. The reasons for this are as follows.

* Issues allows for focused discussion on a single issue
* It is easy for later users to search for issues
* Developers can do task tracking through Issues
* Users can view the progress of various tasks through Issues, open and transparent

However, various groups cannot achieve the above functions. On the contrary, various groups are a manifestation of closed project information and go against the purpose of open source. Some open source projects will intentionally block the flow of information to earn consultation or service fees, but this is not the purpose of Turms.

In practice, groups and even video conferences are more often used for quick discussions among developers internally, especially in the early stages of drafting, but the final results of the discussion and the key issues involved are still recorded in Issues or documents to facilitate users and developers to understand the ins and outs of a problem.

### Can I Ask "Newbie Questions"?

There are no so-called "newbie questions" in the Turms project, only "questions related to the Turms project" and "questions unrelated to the Turms project." Everyone may appear "not very professional" when they encounter a new field, and as newcomers, we hope that there will be more goodwill and tolerance from people in this field. Similarly, as long as it is a question related to the Turms project, we will reply. And when encountering "basic questions", we usually think not "this question is terrible," but "can we add some documents, or optimize the documents to provide more guidance to new users". Therefore, users do not need to worry about asking so-called "newbie questions."

In addition, there is an attitude problem. As long as everyone respects each other, any question can be discussed. The common unacceptable attitudes are: 1. Not reading the documentation, not checking Issues first, and not willing to think before asking directly; 2. Condescending.

Of course, learning how to ask questions is also a very interesting thing. For details, please refer to ["How To Ask Questions The Smart Way"](https://github.com/ryanhanwu/How-To-Ask-Questions-The-Smart-Way).

### About Upstream First

Directly interacting with the open source community and solving problems at the source is called upstream first. 

For Turms, upstream first mainly involves two aspects: communication and code feedback.

* Communication: Before doing a feature or fixing a bug, it is best to open an [issue](https://github.com/turms-im/turms/issues/new) on GitHub in advance. Some features may seem common and easy to implement, but Turms currently does not have them implemented. It's possible that this seemingly simple feature often involves many details, such as:

  * Are there any other related or extended requirements for this requirement?
  * Can this requirement be implemented in this way? Can all related features be implemented in this way? Does the code implementation need to be separate? Is the code implementation universal? Can this template implement almost all related requirements?
  * Can it be implemented in both single-machine and distributed scenarios?
  * From a different business perspective or technical perspective, is there a better design and implementation?

  Therefore, a "seemingly" simple requirement may involve a large amount of requirement analysis and technical analysis. If developers silently implement some features locally, they will face a series of issues mentioned above when giving back the code. If major design problems are discovered during the implementation at this time, some previous efforts may be wasted (of course, there are still gains, at least knowing that "there is room for optimization in the current solution"). **Therefore, when facing complex features, developers should be mentally prepared for "design may be overturned repeatedly."**

  **To minimize this situation, when designing and implementing complex features, it is best for developers to initiate a new discussion in [Issue](https://github.com/turms-im/turms/issues/new), so as to reduce the number of times of design being overturned and save developers' time and effort.**

  Note: Sometimes, even if the design is completed in advance, more ingenious designs may be discovered during the implementation, and the more complex the function, the more design iterations it usually involves. However, these "overturned/half-overturned" iterations are best discussed and developed repeatedly before the code is released, rather than discovering them after the code is released.

  Note: Because of the complexity of requirements, many "seemingly" issues on GitHub Issues are in "pending". Many feature-related issues are just seeds that developers need to do more detailed requirement analysis, design, and coding, and the most difficult thing is usually requirement analysis, which needs to clarify "what needs to be done", and developers need to consider both current and future requirements, and prevent over-design. This is also why Turms documentation mentions several times that "the design and implementation of IM business functions are far more difficult than the design and implementation of technology middleware".

* Reduce your maintenance costs and facilitate the continuous merging of upstream updates. If a developer forks the Turms project for complex secondary development, they will face a long-term maintenance problem: if the developer wants to use upstream's new code, they need to constantly adapt their own branch, and the faster upstream Turms server updates, the greater the developer's adaptation workload. There may even be logical conflicts that the developer is not aware of.

  On the contrary, if developers give back the code to upstream, such problems will not occur. Because we will not only maintain these feedbacked codes together, but also consider whether these new designs and these feedbacked codes are consistent in design when designing other new related functional modules for Turms.

* Reduce maintenance conflicts and avoid overturning local implementations repeatedly. Developers may have added some new features or fixed some bugs locally, but have not given back. After a period of time, developers may find that upstream considers the functionality they have implemented to be more thoughtful and complete, and the bug fixes are more ingenious (readers can read about the difficulty of Turms server-side bugs in [Task Difficulty](https://turms-im.github.io/docs/server/development/redevelopment#%E6%9C%8D%E5%8A%A1%E7%AB%AF)). Ultimately, developers have to revert all their original work, then re-pull upstream and start over again. The workload among them is painful to think about, and the more developers change locally, the more conflicts there may be.