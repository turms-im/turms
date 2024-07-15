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

### Can I plagiarize Turms?

Yes, you can. The reason is actually quite simple. The author of Turms has never intended to profit from Turms, whether directly or indirectly (of course, if someone insists on sending money to my bank account, that's fine too), so even if someone plagiarizes, there is no conflict of interest with the author of Turms.

As for the so-called "fame," my understanding is that "it determines how many R.I.P. messages you can get in the projects/forums you maintain on the day you die, in order to achieve a higher ranking in the underworld popularity ranking," so doing a good job on open source projects may allow open source authors to accumulate some virtue, but this benefit is indeed not attractive to me.

Moreover, most "software engineers" are not qualified to maintain the Turms project. Readers can observe some similar open source IM projects when they have time. Every time I read the source code of such projects, I sigh, "Is there really an interviewer who would give an interview opportunity to someone with such a system design level and coding level? If everyone is a qualified software engineer, how can they accept the projects with such a quality?" Then I came up with a guess: **Is there now not only open source for resumes, but also open source for the bottom market?** I have to admire the "innovative" awareness of some peers who are "far ahead."

Therefore, as long as you or your team does not claim that your inferior code is written by the author of Turms when you plagiarize, I will be satisfied.

Even if you encounter some problems when plagiarizing Turms, you can also ask questions in Issue as long as the question meets the requirements mentioned in [How To Ask Questions The Smart Way](https://github.com/selfteaching/How-To-Ask-Questions-The-Smart-Way/blob/master/How-To-Ask-Questions-The-Smart-Way.md), and you can still get free answers. You can even sell your courses with my free answers (indeed, some other IM open source authors have consulted me about IM technical solutions by email, I answered for free, and then they sold the courses).

### Can I not use Turms, but at the same time get IM-related technical solutions for free by asking questions?

Yes, you can, as long as your question meets the requirements mentioned in [How To Ask Questions The Smart Way](https://github.com/selfteaching/How-To-Ask-Questions-The-Smart-Way/blob/master/How-To-Ask-Questions-The-Smart-Way.md).

This principle is actually quite simple. People with work experience will have the experience of cross-departmental cooperation and will have dealt with all kinds of people. We will always encounter some unqualified people (people who are unqualified in professional skills, workplace skills, work attitude, such as a sense of responsibility), and they will throw the problems they encounter to others without thinking, and even let other departments help them solve the problem in a commanding tone. How will you treat such colleagues? If they are your subordinate, will you let them pass the probation period?

Of course, there are also people with professional skills, who are serious and responsible for their work, and actively help solve the problems encountered by colleagues in other departments, even if solving these problems does not benefit his performance. Therefore, when you think of this person, you think of "he/she is serious, responsible, proactive, and reliable every time." If such a colleague comes to you for help, how will you treat such colleagues? At least for us, even if helping the other party, there is no benefit to our performance, we are also willing to help the other party solve the problem, and even take the initiative to solve the problems that the other party may encounter in the future.

The open source community is also like this. Whether people are willing to solve a problem without benefits sometimes depends on the way of asking questions and the credit of the person who raises the question.

### Can Responses Generated by a Model Similar to ChatGPT be Used for Discussion?

ChatGPT is an excellent memorizer, but its analysis of various technical solutions is quite naive. Engaging in discussions with ChatGPT responses only reflects a lack of critical thinking and a lack of responsibility towards the projects. Therefore, whether we should answer such responses depends on the proportion of responses after removing ChatGPT answers.

Let me mention why we pay so much attention to the issue of "attitude." In fact, engineers with work experience have probably had similar experiences: their work depends on the cooperation of other teams. Although certain tasks may be technically simple, they can become stalled due to the laziness and negative cooperation of other team members, making progress on their own projects extremely difficult. Therefore, in projects that require team collaboration, addressing technically manageable issues on one's own is usually the easiest part, while motivating and coordinating various project teams to work together and complete tasks by the deadline is the most challenging and demanding aspect.

Some engineers without work experience might consider technical expertise as the primary survival skill for engineers. However, a responsible attitude is actually the most critical survival skill in the workplace or community (of course, if someone is genuinely responsible for a project, their technical skills won't be lacking either). Apart from specific domains, for most projects, the technical competence displayed by most qualified engineers is quite similar. The real differentiation lies in their level of dedication and responsibility towards a project.

Therefore, to demonstrate a responsible attitude, please refrain from directly using ChatGPT generated responses to participate in discussions.

#### How to Identify Responses Generated by a Model Similar to ChatGPT

1. The writing style generated by GPT is often too apparent and can be manually recognized.
2. Use the open-source model from Hugging Face, [Hello-SimpleAI/chatgpt-detector-roberta](https://huggingface.co/Hello-SimpleAI/chatgpt-detector-roberta), to detect responses generated by ChatGPT-like models online.
3. Even as GPT continues to develop and display more diverse writing styles, there are now many pre-trained language models and various corpora available. Therefore, it's possible to train a new model to detect GPT-like responses based on transfer learning. This process can be relatively fast, taking just one day, or slower, taking 2-3 days.

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

### About Contacting Turms Author for Private Chat and Custom Development

If readers' teams are interested in doing redevelopment themselves, they can directly refer to the article on [Redevelopment](https://turms-im.github.io/docs/en-US/server/development/redevelopment.html).

For users who wish to pay Turms' author for custom development, it's worth noting that Turms' author generally only accepts unpaid development for common needs (yes, generally, only unpaid development for the community). The reason for this is quite simple; Turms' author doesn't lack money, and even if the Turms project incurs a loss of several tens of thousands of Chinese yuan every year, we can still ensure the continuous operation of the Turms project because we never intended to profit from it in the first place. So, either we will only accept a very high offer that's hard to refuse, or we will only accept unpaid development for the community.

Therefore, unless you are prepared to offer a very high price, it's not advisable to try to contact Turms' author for custom development. If you genuinely want Turms' author to prioritize fulfilling your requirements, you can describe your needs clearly and post them in Issues, and then we will schedule them based on the cost-effectiveness of the requirements and your respect for the requirements you've proposed.

Of course, if you are even willing to pay a high fee for custom development to Turms' author, I also recommend considering commercial solutions directly, even though their development level, work attitude, and work responsibility are probably not as good as Turms' author. Of course, this mainly depends on which country and company's solution you decide to adopt.

Compared to free development, custom development differs in the following aspects:

- A complete, phased project schedule will be provided, including design, development, testing, delivery, and so on.

- Assistance with designing requirements. Readers might wonder why they need Turms' author to design requirements if they already want custom developmpent. This is much like what Henry Ford said, "If I had asked people what they wanted, they would have said faster horses." What users ask for may not necessarily be what they truly need, and having insights into users' real needs is one of the essential skills required for engineers.

- Guaranteed fixed working hours. During this time, only project-related custom design, development, testing, deployment, and addressing various questions will be done.

  Of course, all of the above is done by Turms' author during their off-hours.

If some users are concerned that Turms' author might intentionally slow down the development and release progress of the features they want due to not having paid, this won't happen either, because Turms' author doesn't lack money and doesn't intend to profit from open-source, so there's no motivation to intentionally delay.