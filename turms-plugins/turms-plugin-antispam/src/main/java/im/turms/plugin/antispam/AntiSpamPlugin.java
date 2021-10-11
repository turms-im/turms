package im.turms.plugin.antispam;

import im.turms.server.common.plugin.TurmsExtension;
import im.turms.server.common.plugin.TurmsPlugin;

import java.util.Set;

/**
 * @author James Chen
 */
public class AntiSpamPlugin extends TurmsPlugin {

    @Override
    public Set<Class<? extends TurmsExtension>> getExtensions() {
        return Set.of(AntiSpamHandler.class);
    }

}
