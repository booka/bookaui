package net.boklab.entrance.client;

import net.boklab.project.client.ProjectGinjector;

public interface EntranceGinjector extends ProjectGinjector {
    EntrancePresenter getEntrancePresenter();
}
