/*
 * Copyright (c) 2021, Peter Abeles. All Rights Reserved.
 *
 * This file is part of Efficient Java Matrix Library (EJML).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ejml.dense.row.decomposition.hessenberg;

import org.ejml.EjmlStandardJUnit;
import org.ejml.UtilEjml;
import org.ejml.data.DMatrixRMaj;
import org.ejml.dense.row.MatrixFeatures_DDRM;
import org.ejml.dense.row.RandomMatrices_DDRM;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TestHessenbergSimilarDecomposition_MT_DDRM extends EjmlStandardJUnit {
	int size = 100;

	@Test
	void compareToSingle() {
		DMatrixRMaj A = RandomMatrices_DDRM.rectangle(size,size,-1,1,rand);
		DMatrixRMaj B = A.copy();

		var algSingle = new HessenbergSimilarDecomposition_DDRM(size);
		var algMT = new HessenbergSimilarDecomposition_MT_DDRM(size);

		assertTrue(algSingle.decompose(A));
		assertTrue(algMT.decompose(B));

		assertTrue(MatrixFeatures_DDRM.isEquals(A,B, UtilEjml.TEST_F64));

		assertTrue(MatrixFeatures_DDRM.isEquals(algSingle.getH(null), algMT.getH(null), UtilEjml.TEST_F64));
		assertTrue(MatrixFeatures_DDRM.isEquals(algSingle.getQ(null), algMT.getQ(null), UtilEjml.TEST_F64));
	}
}

