package net.boklab.entrance.client;

import net.boklab.projects.client.ProjectGinjector;

public interface EntranceGinjector extends ProjectGinjector {
    EntrancePresenter getEntrancePresenter();
}
