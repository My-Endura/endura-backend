package com.flohrauer.endurabackend.exercise

import com.flohrauer.endurabackend.musclegroup.MuscleGroup
import org.springframework.data.jpa.domain.Specification
import java.util.*

object ExerciseSpecification {

    fun hasName(name: String?) = Specification<Exercise> { root, _, cb ->
        if(name.isNullOrBlank()) {
            cb.conjunction()
        } else {
            cb.like(root.get(Exercise::name.name), "%${name}%")
        }
    }

    fun containsMuscleGroups(muscleGroupsIds: List<UUID>?) = Specification<Exercise> { root, _, cb ->
        if(muscleGroupsIds.isNullOrEmpty()) {
            cb.conjunction()
        } else {
            val path = root
                .join<MuscleGroup, MuscleGroup>(Exercise::muscleGroups.name)
                .get<UUID>(MuscleGroup::id.name)

            val predicates = muscleGroupsIds.map { cb.equal(path, it) }
            cb.and(*predicates.toTypedArray())
        }
    }
}
