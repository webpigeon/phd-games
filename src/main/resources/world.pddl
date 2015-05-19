(define (domain SAMPLE_WORLD)
  (:requirements [:strips] [:equality] [:typing] [:adl])
  (:predicates (WALKABLE ?X ?Y)
	       )

  (:action ACTION_1_NAME
    [:parameters (?P1 ?P2 ... ?PN)]
    [:precondition PRECOND_FORMULA]
    [:effect EFFECT_FORMULA]
   )

)
