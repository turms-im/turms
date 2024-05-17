/*
 * Copyright (C) 2019 The Turms Project
 * https://github.com/turms-im/turms
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.turms.plugin.livekit.core.proto.webhook;

public final class LivekitWebhook {
    private LivekitWebhook() {
    }

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                LivekitWebhook.class.getName());
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
    }

    static final com.google.protobuf.Descriptors.Descriptor internal_static_livekit_WebhookEvent_descriptor;
    static final com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_livekit_WebhookEvent_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    private static final com.google.protobuf.Descriptors.FileDescriptor descriptor;
    static {
        java.lang.String[] descriptorData =
                {"\n\025livekit_webhook.proto\022\007livekit\032\024liveki"
                        + "t_models.proto\032\024livekit_egress.proto\032\025li"
                        + "vekit_ingress.proto\"\227\002\n\014WebhookEvent\022\r\n\005"
                        + "event\030\001 \001(\t\022\033\n\004room\030\002 \001(\0132\r.livekit.Room"
                        + "\022-\n\013participant\030\003 \001(\0132\030.livekit.Particip"
                        + "antInfo\022(\n\013egress_info\030\t \001(\0132\023.livekit.E"
                        + "gressInfo\022*\n\014ingress_info\030\n \001(\0132\024.liveki"
                        + "t.IngressInfo\022!\n\005track\030\010 \001(\0132\022.livekit.T"
                        + "rackInfo\022\n\n\002id\030\006 \001(\t\022\022\n\ncreated_at\030\007 \001(\003"
                        + "\022\023\n\013num_dropped\030\013 \001(\005Bt\n*im.turms.plugin"
                        + ".livekit.core.proto.webhookP\001Z#github.co"
                        + "m/livekit/protocol/livekit\252\002\rLiveKit.Pro"
                        + "to\352\002\016LiveKit::Protob\006proto3"};
        descriptor = com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
                descriptorData,
                new com.google.protobuf.Descriptors.FileDescriptor[]{
                        im.turms.plugin.livekit.core.proto.models.LivekitModels.getDescriptor(),
                        im.turms.plugin.livekit.core.proto.egress.LivekitEgress.getDescriptor(),
                        im.turms.plugin.livekit.core.proto.ingress.LivekitIngress
                                .getDescriptor(),});
        internal_static_livekit_WebhookEvent_descriptor = getDescriptor().getMessageTypes()
                .get(0);
        internal_static_livekit_WebhookEvent_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                        internal_static_livekit_WebhookEvent_descriptor,
                        new java.lang.String[]{"Event",
                                "Room",
                                "Participant",
                                "EgressInfo",
                                "IngressInfo",
                                "Track",
                                "Id",
                                "CreatedAt",
                                "NumDropped",});
        descriptor.resolveAllFeaturesImmutable();
        im.turms.plugin.livekit.core.proto.models.LivekitModels.getDescriptor();
        im.turms.plugin.livekit.core.proto.egress.LivekitEgress.getDescriptor();
        im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.getDescriptor();
    }

    // @@protoc_insertion_point(outer_class_scope)
}