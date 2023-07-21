package lec.baekseokuni.indyholder.verification;

import androidx.annotation.Nullable;

import java.util.List;

import kr.co.bdgen.indywrapper.data.Credential;
import kr.co.bdgen.indywrapper.data.payload.RequestedAttribute;

public class RequestedAttributeSearchedItem {
    private final String requestedAttributeKey;
    private final RequestedAttribute requestedAttribute;
    private final List<Credential> searchCredentialList;

    @Nullable
    private Credential selectedCredential;

    public RequestedAttributeSearchedItem(String requestedAttributeKey, RequestedAttribute requestedAttribute, List<Credential> searchCredentialList) {
        this.requestedAttributeKey = requestedAttributeKey;
        this.requestedAttribute = requestedAttribute;
        this.searchCredentialList = searchCredentialList;
        if (searchCredentialList.isEmpty())
            return;
        selectedCredential = searchCredentialList.get(0);
    }

    public String getRequestedAttributeKey() {
        return requestedAttributeKey;
    }

    public RequestedAttribute getRequestedAttribute() {
        return requestedAttribute;
    }

    public List<Credential> getSearchCredentialList() {
        return searchCredentialList;
    }

    @Nullable
    public Credential getSelectedCredential() {
        return selectedCredential;
    }
}
