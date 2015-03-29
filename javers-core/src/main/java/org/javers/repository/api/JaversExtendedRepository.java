package org.javers.repository.api;

import org.javers.common.collections.Optional;
import org.javers.core.commit.Commit;
import org.javers.core.commit.CommitId;
import org.javers.core.json.JsonConverter;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.core.metamodel.object.GlobalId;
import org.javers.repository.jql.GlobalIdDTO;
import org.javers.core.metamodel.object.GlobalIdFactory;

import java.util.List;

import static org.javers.common.validation.Validate.argumentIsNotNull;

/**
 * @author bartosz walacik
 */
public class JaversExtendedRepository implements JaversRepository {
    private final JaversRepository delegate;
    private final GlobalIdFactory globalIdFactory;

    public JaversExtendedRepository(JaversRepository delegate, GlobalIdFactory globalIdFactory) {
        this.delegate = delegate;
        this.globalIdFactory = globalIdFactory;
    }

    public List<CdoSnapshot> getStateHistory(GlobalIdDTO globalIdDTO, int limit){
        argumentIsNotNull(globalIdDTO);
        return delegate.getStateHistory(globalIdFactory.createFromDto(globalIdDTO), limit);
    }

    @Override
    public List<CdoSnapshot> getStateHistory(GlobalId globalId, int limit) {
        argumentIsNotNull(globalId);
        return delegate.getStateHistory(globalId, limit);
    }

    @Override
    public List<CdoSnapshot> getPropertyHistory(GlobalId globalId, String propertyName, int limit) {
        return null;
    }

    public Optional<CdoSnapshot> getLatest(GlobalIdDTO globalCdoIdDTO) {
        argumentIsNotNull(globalCdoIdDTO);
        return delegate.getLatest(globalIdFactory.createFromDto(globalCdoIdDTO));
    }

    @Override
    public Optional<CdoSnapshot> getLatest(GlobalId globalId) {
        argumentIsNotNull(globalId);
        return delegate.getLatest(globalId);
    }

    @Override
    public void persist(Commit commit) {
        delegate.persist(commit);
    }

    @Override
    public CommitId getHeadId() {
        return delegate.getHeadId();
    }

    @Override
    public void setJsonConverter(JsonConverter jsonConverter) {
    }

    @Override
    public void ensureSchema() {
        delegate.ensureSchema();
    }
}
