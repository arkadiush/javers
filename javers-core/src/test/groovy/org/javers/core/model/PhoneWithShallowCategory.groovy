package org.javers.core.model

import org.javers.core.metamodel.annotation.ShallowReference
import javax.persistence.Id

/**
 * @author michal wesolowski
 */
class PhoneWithShallowCategory {
    @Id
    Long id
    String number = "123"
    @ShallowReference
    CategoryC shallowCategory
    @ShallowReference
    Set<CategoryC> shallowCategories
    @ShallowReference
    Map<String, CategoryC> shallowCategoryMap
    CategoryC deepCategory

    @Id
    Long getId() {
        id
    }

    @ShallowReference
    CategoryC getShallowCategory() {
        shallowCategory
    }

    CategoryC getDeepCategory() {
        deepCategory
    }
}
