                                        ;22. What is the total of all the name scores in the file?
                                        ;=========================================================
(println (into [] (.split (String/replace read-line "\"" "")) ","))
