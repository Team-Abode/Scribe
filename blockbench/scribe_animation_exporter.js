(function() {
    let exportScribeAnimations

    Plugin.register('scribe_animation_exporter', {
        title: 'Scribe Animation Exporter',
        author: 'Team Abode',
        icon: 'icon-format_java',
        description: 'Exports Blockbench animations as the JSON format for Scribe Animations --- for Scribe Library',
        about: 'Go to Animation -> Export Scribe Animation...',
        tags: ["Minecraft: Java Edition"],
        version: '1.0.0',
        variant: 'both',
        onload() {
            exportScribeAnimations = new Action('export_scribe_animation', {
                name: 'Export Scribe Animation...',
                description: 'Export a selection of animations as Scribe Animations',
                icon: 'movie',
                click: function() {
                    const form = {}

                    for (const animation of Animator.animations) {
                        form[animation.name] = {
                            label: animation.name,
                            type: "checkbox",
                            value: true
                        }
                    }

                    const formDialog = new Dialog({
                        id: "scribe_animation_export",
                        title: "dialog.animation_export.title",
                        form,
                        onConfirm(formResult) {
                            formDialog.hide()

                            let animationExportIndex = 0

                            for (let selectedAnimation in form) {
                                const scribe_animation = compileScribeAnimation(Animator.animations[animationExportIndex])

                                animationExportIndex++

                                selectedAnimation = form[selectedAnimation]

                                if (!selectedAnimation.value) continue

                                Blockbench.export({
                                    resource_id: 'animation',
                                    type: 'JSON Animation',
                                    extensions: ['json'],
                                    name: (selectedAnimation.label || "scribe_animation"),
                                    content: autoStringify(scribe_animation),
                                })
                            }
                        }
                    })

                    formDialog.show()
                }
            })
            MenuBar.addAction(exportScribeAnimations, 'animation')
        },
        onunload() {
            exportScribeAnimations.delete()
        }
    })

    function compileScribeAnimation(animation) {
        const scribe_animation = {
            length: animation.length,
            looping: animation.loop === "loop" ? true : false,
            bones: {}
        }

        for (let animator in animation.animators) {
            animator = animation.animators[animator]

            if (animator.animation !== undefined) {
                scribe_animation.bones[animator.group.name] = {}

                const currentBone = scribe_animation.bones[animator.group.name]

                if (animator.position.length > 0) {
                    currentBone.position = keyframeParser(animator.position)
                }

                if (animator.rotation.length > 0) {
                    currentBone.rotation = keyframeParser(animator.rotation)
                }

                if (animator.scale.length > 0) {
                    currentBone.scale = keyframeParser(animator.scale)
                }

                if (Object.keys(currentBone).length === 0) {
                    delete scribe_animation.bones[animator.group.name]
                }
            }
        }

        return scribe_animation
    }

    function keyframeParser(keyframeArray) {
        const parsedKeyframes = {}

        for (let keyframe in keyframeArray) {

            keyframe = keyframeArray[keyframe]

            if (keyframe.time === undefined) continue

            let keyframeDataPoint = {
                x: 0,
                y: 0,
                z: 0
            }

            for (const dataPoint in keyframe.data_points) {
                keyframeDataPoint.x = keyframe.data_points[dataPoint].x
                keyframeDataPoint.y = keyframe.data_points[dataPoint].y
                keyframeDataPoint.z = keyframe.data_points[dataPoint].z
                break
            }


            parsedKeyframes[parseFloat(keyframe.time.toString())] = {
                interpolation: keyframe.interpolation,
                value: [
                    parseFloat(keyframeDataPoint.x.toString()),
                    parseFloat(keyframeDataPoint.y.toString()),
                    parseFloat(keyframeDataPoint.z.toString())
                ]
            }


        }

        return parsedKeyframes
    }
})()