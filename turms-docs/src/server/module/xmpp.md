# XMPP

## Background

XMPP is an open instant messaging protocol based on XML.

Turms does not use the XMPP protocol itself because:

* It is very inefficient:
  * The data format uses redundant and inefficient XML, and its metadata is often larger than the actual transmitted data.
  * In XMPP's process design, there are many inefficient designs, such as converting user avatar images into Base64 text for transmission, and the server needs to actively push the modified personal information of a user to other users who subscribe to their presence in the roster.
* It has poor scalability. Some articles may say that XMPP has strong scalability, but this "strong scalability" is only relative to those protocols with little scalability. A protocol with truly strong scalability is definitely a self-developed one.

However, considering the following two points, we plan to adapt the Turms server to support the XMPP protocol in the near future:

* The XMPP ecosystem happens to make up for a deficiency of Turms, which some developers have feedbacked under the Turms project: it is still quite complicated to implement a customized IM application from scratch based on Turms, especially since they need to implement UI interfaces and adapt APIs by themselves. Therefore, Turms is more suitable for teams that want to delve into IM research and development rather than for quick product releases.

  XMPP has a relatively rich client-side ecosystem, so as long as the Turms server is slightly adapted, it can provide services to XMPP clients. This allows users to quickly offer services through various UI-based XMPP clients while enjoying the benefits of Turms. When users want to create their own dedicated IM application, they can gradually phase out XMPP clients and transition to the Turms client.

  Note: Due to Turms' positioning, we do not consider tasks related to "providing UI-based clients" in our long-term plans. In other words, we will only consider providing UI-based clients after we have released customized stress testing platforms, data analysis platforms, and implemented various extensions and bug fixes for Turms. Therefore, the priority of this task is very low.

* Most well-known XMPP open source server projects not only have outdated technical architecture and stacks, but also poor code quality and engineering capabilities. For example, the Tigase project, as an open source project that has been developed for decades, still makes a large number of rookie mistakes such as comparing strings using `==`, or mixing data models with business logic without any code design capabilities, which is astonishing in terms of development ability.

  Although some open source XMPP servers may advertise their "scalable" architecture, their scalability is incomparable to that of Turms. Turms is a project that tries to achieve the ultimate in all aspects (including scalability) from a true sense of architecture, code implementation, database design, etc., so in the field of medium-to-large IM, Turms can strike a blow against them.

Note: In fact, we do not have a plan to replace other XMPP servers with Turms server because the positioning of XMPP servers and Turms server are very different. One of the main goals of XMPP servers is to achieve open communication for instant messaging (just like email), but the support of the XMPP protocol in Turms server is mainly to allow users to quickly communicate with Turms server using XMPP clients, so as to provide services to the world quickly. Moreover, we do not have a plan to support the communication between Turms servers and other XMPP servers.

## Implementation Principle

* The turms-gateway server first implements a customized XMPP server internally.

  Note: Customization is necessary because Turms does not need some of the features specified by the XMPP protocol, so there is no need to implement them. However, the customized XMPP server can still be compatible with standard XMPP clients.

* When the XMPP server receives requests from XMPP clients, it will convert these requests into corresponding Turms service calls. Therefore, from the perspective of subsequent calls, XMPP client requests and Turms client requests follow similar logic, ultimately achieving interoperability between XMPP clients and Turms clients.

  Note:

  * Both use "similar logic" because their business processes are slightly different and not a one-to-one relationship.
  * XMPP and Turms clients share the same account system, so one account can be used to log in to both XMPP and Turms clients.
  * XMPP clients do not know about the Turms clients, and vice versa. The reason why they can communicate with each other is that the turms-gateway will convert the data into the protocol format they can understand before sending it.