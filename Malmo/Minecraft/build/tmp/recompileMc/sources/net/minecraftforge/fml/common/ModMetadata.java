/*
 * Forge Mod Loader
 * Copyright (c) 2012-2013 cpw.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 *
 * Contributors:
 *     cpw - implementation
 */

package net.minecraftforge.fml.common;

import java.util.List;
import java.util.Set;

import net.minecraftforge.fml.common.functions.ModNameFunction;
import net.minecraftforge.fml.common.versioning.ArtifactVersion;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.gson.annotations.SerializedName;

/**
 * @author cpw
 *
 */
public class ModMetadata
{
    @SerializedName("modid")
    public String modId;
    public String name;
    public String description = "";

    public String url = "";
    public String updateUrl = "";

    public String logoFile = "";
    public String version = "";
    public List<String> authorList = Lists.newArrayList();
    public String credits = "";
    public String parent = "";
    public String[] screenshots;

    // this field is not for use in the json
    public transient ModContainer parentMod;
    // this field is not for use in the json
    public transient List<ModContainer> childMods = Lists.newArrayList();

    public boolean useDependencyInformation;
    public Set<ArtifactVersion> requiredMods = Sets.newHashSet();
    public List<ArtifactVersion> dependencies = Lists.newArrayList();
    public List<ArtifactVersion> dependants = Lists.newArrayList();
    // this field is not for use in the json
    public transient boolean autogenerated;

    public ModMetadata()
    {
    }

    public String getChildModCountString()
    {
        return String.format("%d child mod%s", childMods.size(), childMods.size() != 1 ? "s" : "");
    }

    public String getAuthorList()
    {
        return Joiner.on(", ").join(authorList);
    }

    public String getChildModList()
    {
        return Joiner.on(", ").join(Lists.transform(childMods, new ModNameFunction()));
    }

    public String printableSortingRules()
    {
        return "";
    }
}